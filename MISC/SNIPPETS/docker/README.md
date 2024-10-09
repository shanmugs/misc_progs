# Docker

## How to restart all the dockers containers

```bash
docker restart $(docker ps -q)
```

## How to use docker command without be root

1. Add the group `docker` if it's necesary.

```bash
sudo groupadd docker
```

2. Add your user to the docker group:

```bash
sudo usermod -aG docker $USER
```

3. Logout and login again to see the changes.

## How to see containers running by name and image

```bash
docker ps --format "{{.Names}} {{.Image}}"
```

## See container stats consumption

```bash
docker stats
```

## Clean machine about old and unused containers, images, networks 

**(Caution: It can remove useful resources)**

```bash
docker system prune
```

[More info](https://docs.docker.com/engine/reference/commandline/system_prune/)

## Copy files from/to container

```bash
docker cp <container id>:<container directory> <file>
docker cp <file> <container id>:<container directory>
```

***

- [Home](/README.md)
- [Docker](/docker/README.md)
- [Git](/git/README.md)
- [Kubernetes](/k8s/README.md)