package com.cadastrodeeventos.api.service;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import com.cadastrodeeventos.api.domain.event.Event;
import com.cadastrodeeventos.api.domain.event.EventRequestDTO;
import com.cadastrodeeventos.api.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final S3Client s3Client;
    @Value("${aws.bucket.name}")
    private String bucketName;
    @Value("${aws.region}")
    private String awsRegion;


    public EventService(EventRepository eventRepository, S3Client s3Client) {
        this.s3Client = s3Client;
        this.eventRepository = eventRepository;
    }

    public Event createEvent(EventRequestDTO data) {
        String imgUrl = null;

        if (data.image() != null) {
            imgUrl = this.uploadImage(data.image());
        }

        Event newEvent = new Event();
        newEvent.setTitle(data.title());
        newEvent.setDescription(data.description());
        newEvent.setEventUrl(data.eventUrl());
        newEvent.setDate(new Date(data.date()));
        newEvent.setImgUrl(imgUrl);
        newEvent.setRemote(data.remote());
        eventRepository.save(newEvent);
        return newEvent;
    }

    private String uploadImage(MultipartFile multipartFile) {
        String filename = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        try {
            PutObjectRequest putOb = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(filename)
                    .build();
            s3Client.putObject(putOb, RequestBody.fromByteBuffer(ByteBuffer.wrap(multipartFile.getBytes())));
            GetUrlRequest request = GetUrlRequest.builder()
                    .bucket(bucketName)
                    .key(filename)
                    .build();

            return s3Client.utilities().getUrl(request).toString();
        } catch (Exception e) {
            System.out.println("erro ao subir arquivo");
            System.out.println(e.getMessage());
            return "";
        }
    }
    }


