# Kubernetes - PODs

## Get all pods

```bash
kubectl get pods
```

## Get all info and details of a pod

```bash
kubectl describe pod <pod name>
```

## How to connect to one pod

```bash
kubectl exec -it <pod name> /bin/bash
```

## Copy file inside a pod

```bash
kubectl cp <file to copy> <pod name>:<path to copy file inside pod>
```

## See logs of one pod

```bash
kubectl logs <name of pod> -f --timestamps
```

## How to see the pods how consume more resources

```bash
kubectl top pods
```

***

- [Home](/README.md)
- [Docker](/docker/README.md)
- [Git](/git/README.md)
- [Kubernetes](/k8s/README.md)
