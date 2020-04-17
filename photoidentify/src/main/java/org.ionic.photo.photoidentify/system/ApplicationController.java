package org.ionic.photo.photoidentify.system;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.Label;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import com.amazonaws.auth.*;
import org.ionic.photo.photoidentify.model.*;
import org.springframework.http.MediaType;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
class ApplicationController {

    @PostMapping(value="/photo/identify", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<Label>> identifyJson(@RequestBody PhotoRequest photoRequest) {

        //System.out.println("values is " + photoRequest.getImageValue());
        String imageValue = photoRequest.getImageValue();
        String deviceId = photoRequest.getDeviceId();

//        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
//                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
//                .build();

        ByteBuffer imageBytes = ByteBuffer.wrap(Base64.decodeBase64(imageValue));
        /*try (InputStream inputStream = new FileInputStream(new File(photo))) {
            imageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
        }
        */

        BasicAWSCredentials awsCreds = new BasicAWSCredentials("<key_id>", "<secret_id>");

        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();

        DetectLabelsRequest request = new DetectLabelsRequest()
                .withImage(new Image()
                        .withBytes(imageBytes))
                .withMaxLabels(3)
                .withMinConfidence(77F);

        List <Label> labels = new ArrayList<>();
        try {

            DetectLabelsResult result = rekognitionClient.detectLabels(request);
            labels = result.getLabels();

            for (Label label: labels) {
                System.out.println(label.getName() + ": " + label.getConfidence().toString());
            }

        } catch (AmazonRekognitionException e) {
            e.printStackTrace();
        }

        if (labels.size() == 0) {
            Label noData = new Label();
            noData.setName("NO MATCH!");
            labels.add(noData);
        }

        return new ResponseEntity<List<Label>>(labels, HttpStatus.OK);
    }

    private static final Logger logger = LoggerFactory.getLogger(ApplicationController.class);

}