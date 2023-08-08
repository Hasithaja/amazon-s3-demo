# AWS S3 Bucket Operations using AWS Java SDK

This repository provides examples of how to perform common operations with Amazon S3 buckets using the AWS Java SDK. The following operations are covered in this README:

1. [List S3 Buckets](#list-s3-buckets)
2. [Create an S3 Bucket](#create-an-s3-bucket)
3. [List Objects in an S3 Bucket](#list-objects-in-an-s3-bucket)
4. [Add an Object to an S3 Bucket](#add-an-object-to-an-s3-bucket)
5. [Get an Object from an S3 Bucket](#get-an-object-from-an-s3-bucket)

## Prerequisites

Before running the examples, ensure you have the following set up:

- AWS access key ID and secret access key with proper permissions.
- Java JDK installed on your system.
- AWS SDK for Java added to your project.

## List S3 Buckets

To list all the S3 buckets, you can use the `listBuckets` method.

## Create an S3 Bucket

You can create an S3 bucket using the `createBucket` method. Make sure you follow [best practices](https://docs.aws.amazon.com/AmazonS3/latest/dev/UsingBucket.html) when naming your bucket.

## Add an Object to an S3 Bucket

To add an object (file) to an S3 bucket, you can use the `putObject` method. The content can be a text string or binary data.

## List objects in an S3 Bucket

After adding objects to a bucket, you can again use the `listObjects` method to retrieve a list of object keys.

## Get an Object from an S3 Bucket

You can get an object (file) from an S3 bucket using the `getObject` method.

## Running the Examples

1. Clone this repository to your local machine.
2. Open the examples in your favorite Java IDE.
3. Configure your AWS credentials using environment variables or any other valid credential provider.
4. Update the bucket name and object keys in the Java files as needed.
5. Run the examples to see the output.

## Contributing

Contributions are welcome! If you find any issues or improvements, feel free to open an issue or submit a pull request.