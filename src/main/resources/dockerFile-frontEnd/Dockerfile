FROM node:16

# add app directory
WORKDIR ./client

# Installing dependencies
COPY package*.json .
COPY yarn.lock .
RUN yarn install

# Copying source files
COPY . .

# Building app
RUN yarn build
EXPOSE 3030

# Running the app
CMD ["yarn", "dev"]















