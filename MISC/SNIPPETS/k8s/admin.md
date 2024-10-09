# Kubernetes - Administrate

## How to remove all the node of a cluster

1. Check if you ip was on "Master authorized networks" on k8s cluster.

    - Click edit on cluster.
    - Check the "Master authorized networks".

2. First point to right k8s cluster:

    ```bash
    gcloud container clusters get-credentials <cluster name> --zone <gcp region> --project <gcp project>
    ```

3. Get the list of nodes.

    ```bash
    kubectl get nodes
    ```

4. Drain/disable each node of cluster.

    ```bash
    kubectl drain <node name>
    kubectl drain <node name> --ignore-daemonsets
    ```

5. Delete physically each node.

    ```bash
    kubectl delete node <node name>
    ```

## How to see node stats

```bash
kubectl top nodes
```

## Get info of all contexts

```bash
kubectl config get-contexts
```

## How to change the context

```bash
kubectl config use-context <context ID>
```

## How to see current context

```bash
kubectl config current-context
```

***

- [Home](/README.md)
- [Docker](/docker/README.md)
- [Git](/git/README.md)
- [Kubernetes](/k8s/README.md)
