kubectl apply -f k8s/

To apply these manifests in CI/CD:  
Build and push your Docker image to ECR.
Update the image tag in deployment.yaml (if needed).
Apply the manifests using kubectl after authenticating to your EKS cluster.

PLAN: (self hosted runners on EC2 instances)
1. Deploy on AWS
2. ECR (Elastic Container Registry)
3. EKS (Elastic Kubernetes Service) 
4. Kubernetes manifests (deployment.yaml, service.yaml, ingress.yaml)
5. CI/CD pipeline (GitHub Actions)
6. Setup monitoring and logging (CloudWatch, Prometheus, Grafana)