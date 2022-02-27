
FROM node:latest as node
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build --prod

FROM nginx:alpine
COPY --from=node /app/dist/ui-monitoring /usr/share/nginx/html
COPY /default.conf  /etc/nginx/conf.d/default.conf
