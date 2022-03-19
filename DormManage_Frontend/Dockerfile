FROM node:16-alpine AS builder
WORKDIR /Frontend

ADD . .

# install node modules
RUN npm install
RUN npm run build
# install node server
RUN rm -rf ./node_modules && cd server && npm install

# workdir change to server dir
FROM nginx:alpine as production-stage
WORKDIR /usr/share/nginx/html
RUN rm -rf ./*
COPY --from=builder /Frontend/dist .
COPY nginx.conf /etc/nginx/nginx.conf
EXPOSE 80
# start nginx
ENTRYPOINT ["service", "nginx"]
