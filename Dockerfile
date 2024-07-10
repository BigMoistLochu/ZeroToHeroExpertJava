FROM ubuntu:latest
LABEL authors="krokk"

ENTRYPOINT ["top", "-b"]