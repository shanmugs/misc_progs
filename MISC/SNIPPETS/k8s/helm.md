# Kubernetes - Helm

## How to install `Helm` on K8S

0. Get credentail for your cluster

    ```bash
    gcloud container clusters get-credentials <cluster name> --zone <zone of cluster>
    ```

1. Download the script to install and install it

    ```bash
    curl -LO https://git.io/get_helm.sh
    chmod 700 get_helm.sh
    ./get_helm.sh
    ```

2. Give access to your current user 

    ```bash
    kubectl create clusterrolebinding user-admin-binding \
    --clusterrole=cluster-admin \
    --user=$(gcloud config get-value account)
    ```

3. Create and give access to service account tiller (helm server)

    ```bash
    kubectl create serviceaccount tiller --namespace kube-system
    kubectl create clusterrolebinding tiller-admin-binding \
    --clusterrole=cluster-admin \
    --serviceaccount=kube-system:tiller
    ```

4. Initialize Helm server

    ```bash
    helm init --service-account=tiller
    ```

## How to uninstall Helm

    ```bash
    helm reset
    ```

***

- [Home](/README.md)
- [Docker](/docker/README.md)
- [Git](/git/README.md)
- [Kubernetes](/k8s/README.md)