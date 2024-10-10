# Use the official Node.js image as the base image
FROM node:16-alpine AS builder

# Set the working directory
WORKDIR /app

# Copy the package.json and package-lock.json files
COPY package*.json ./

# Install the dependencies
RUN npm ci

# Copy the application code
COPY . .

# Build the Nest.js application
RUN npm run build

# Use a smaller base image for the runtime
FROM node:16-alpine

# Set the working directory
WORKDIR /app

# Copy the built application from the builder stage
COPY --from=builder /app/dist ./dist

# Install only the production dependencies
RUN npm ci --only=production

# Expose the port the application will run on
EXPOSE 3000

# Start the application
CMD ["node", "dist/main.js"]
