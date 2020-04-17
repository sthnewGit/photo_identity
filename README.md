"# photo_identity" 

1. navigate to identify folder
2. mvn clean install
3. go to upper folder, docker build -t photoidentify:27.0-SNAPSHOT photoidentify/.
4. docker tag photoidentify:27.0-SNAPSHOT 247522201286.dkr.ecr.us-east-1.amazonaws.com/awsguide/photoidentify:27.0-SNAPSHOT
5. aws ecr get-login-password which returns a pwd string
6. aws sts get-caller-identity --output text --query "Account"  which gets an account
7. docker login -u AWS -p [password_string] https://[aws_account_id].dkr.ecr.[region].amazonaws.com
8. docker push 247522201286.dkr.ecr.us-east-1.amazonaws.com/awsguide/photoidentify:27.0-SNAPSHOT push to ECR
9. Use kubectl to set the image:
kubectl set image deployment/photoidentify-deployment photoidentify-container=247522201286.dkr.ecr.us-east-1.amazonaws.com/awsguide/photoidentify:27.0-SNAPSHOT
10. verify: kubectl get pods
11. to see logs: kubectl logs photoidentify-deployment-9bb8c9d4f-vrzls  (id from pods)

reference: https://openliberty.io/guides/cloud-aws.html#pushing-the-images-to-a-container-registry
