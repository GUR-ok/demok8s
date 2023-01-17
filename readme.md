demok8s

### Сборка имейджа:
1. gradle build
2. docker build .
3. docker images
4. docker tag <image_id> gurok/demok8s (можно сразу было "docker build -t gurok/demok8s .")
5. docker login -u <user> -p <password>
6. docker push gurok/demok8s

### Для создания имейджа другой версии приложения:
1. gradle build
2. docker build -t gurok/demok8s:v2 .
3. docker push gurok/demok8s:v2

### Проверка работоспособности:
1. kubectl version
2. minikube status
3. docker version
4. helm version 

### Получение ip ноды и миникуба:
kubectl get nodes
kubectl describe node minikube
minikube ip

### Просмотр ресурсов кластера:
kubectl get all --all-namespaces
kubectl get namespaces
kubectl create namespace <namespace_name>
kubectl get svc
kubectl describe service <svc_name>
kubectl get pods

### Применение манифеста и общение с подами:
kubectl apply -f ./deployment/mypod.yaml
kubectl logs -f <pod_name>
kubectl logs -f -n <namespace> <pod_name> 
kubectl port-forward <pod_name> 8001:8080
kubectl port-forward -n <namespace> <pod_name> 8001:8080
kubectl delete pod <pod_name>
kubectl delete pod -n <namespace> <pod_name>

### Установка через хелм:
helm install <release_name> ./deployment/helm
helm upgrade <release_name> ./deployment/helm
helm list
helm uninstall <release_name>

helm install kafka ./deployment/kafka
helm intall myhelm ./deployment/helm
helm uninstall myhelm

### Настройка nginx ingress балансировщика в кластеер:
minikube addons enable ingress

- В случае ошибки при делое приложения через helm

  Error: INSTALLATION FAILED: Internal error occurred: failed calling webhook "validate.nginx.ingress.kubernetes.io": Post "https://ingress-nginx-controller-admission.ingress-nginx.svc:4
  43/networking/v1/ingresses?timeout=10s": dial tcp 10.111.50.42:443: connect: connection refused

  необходимо выполнить:
    ```
    get ValidatingWebhookConfiguration
    kubectl delete -A ValidatingWebhookConfiguration ingress-nginx-admission
    ```