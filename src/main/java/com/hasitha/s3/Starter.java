package com.hasitha.s3;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class Starter implements CommandLineRunner {

    @Override
    public void run(String... args) {
        // Code to be executed on application startup
        System.out.println("Application started!");

        // Bucket name
        String bucketName = "s3demotest10";
        // Change to your desired object key
        String objectKey = "example-object.txt";
        // Create content for the object
        String objectContent = "Hello, AWS S3!";

        // Create an S3 client using default credentials provider chain
        final AmazonS3 s3 = AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.US_EAST_1)
                .build();

        listBuckets(s3);
//        createBucket(s3, bucketName);
//        putObject(s3, bucketName, objectKey, objectContent);
//        listObjects(s3, bucketName);
//        getObject(s3, bucketName, objectKey);
    }

    private void createBucket(AmazonS3 s3, String bucketName) {
        if (s3.doesBucketExistV2(bucketName)) {
            System.out.format("Bucket %s already exists.\n", bucketName);
        } else {
            try {
                s3.createBucket(bucketName);
                System.out.format("Bucket %s created successfully.\n", bucketName);
            } catch (AmazonS3Exception e) {
                System.err.println(e.getErrorMessage());
            }
        }
    }

    private void listBuckets(AmazonS3 s3) {
        // List all buckets
        List<Bucket> buckets = s3.listBuckets();

        // Print bucket names
        System.out.println("List of buckets:");
        for (Bucket bucket : buckets) {
            System.out.println(bucket.toString());
        }
    }

    private void putObject(AmazonS3 s3, String bucketName, String objectKey, String content) {
        // Convert the content to an input stream
        InputStream inputStream = new ByteArrayInputStream(content.getBytes());

        // Create metadata for the object
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(content.getBytes().length);

        // Create a PutObjectRequest to upload the object
        PutObjectRequest request = new PutObjectRequest(bucketName, objectKey, inputStream, metadata);

        // Upload the object
        try {
            s3.putObject(request);
            System.out.println("Object created successfully.");
        } catch (AmazonS3Exception e) {
            System.err.println(e.getErrorMessage());
        }
    }

    private void getObject(AmazonS3 s3, String bucketName, String objectKey) {
        // Get the object
        S3Object s3Object = s3.getObject(bucketName, objectKey);

        // Get the input stream of the object's content
        try (S3ObjectInputStream objectInputStream = s3Object.getObjectContent()) {
            // Read the content from the input stream
            byte[] contentBytes = objectInputStream.readAllBytes();
            String content = new String(contentBytes);

            System.out.println("Object content:");
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listObjects(AmazonS3 s3, String bucketName) {
        // List objects in the bucket
        ObjectListing objectListing = s3.listObjects(bucketName);

        // Print object keys
        System.out.println("List of objects:");
        for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            System.out.println(objectSummary.toString());
        }
    }

    private void deleteObject(AmazonS3 s3, String bucketName, String objectKey) {
        // Delete the object
        try {
            s3.deleteObject(bucketName, objectKey);
            System.out.println("Object deleted successfully.");
        } catch (AmazonS3Exception e) {
            System.err.println(e.getErrorMessage());
        }
    }
}