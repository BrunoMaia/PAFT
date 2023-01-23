import express from 'express';
import cookieParser from 'cookie-parser';
import logger from 'morgan';
import swaggerJSDoc from 'swagger-jsdoc';
import OpenApiValidator from 'express-openapi-validator';
import swaggerUi from 'swagger-ui-express';
import { dirname } from 'path';
import { fileURLToPath } from 'url';
import resolver from './esmresolver.mjs';
import { JWT_SECURITY } from '../components/user/jwt.mjs';

const __dirname = dirname(fileURLToPath(import.meta.url));

const app = express();

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());

const swaggerOptions = {
    swaggerDefinition: {
        openapi: "3.0.3",
        info: {
            title: "Finanças Server",
            version: "1.0.0",
            description: "API de finanças"
        },
        servers: [{
            url: "http://localhost:3002/api",
            description: "Finanças Server"
        }]
    },
    apis: [
        __dirname + "/../components/**/*.yaml",
        __dirname + "/../components/**/*.mjs",
    ]
};

const swaggerDocs = swaggerJSDoc(swaggerOptions);
delete swaggerDocs.channels;

app.use("/api-docs",swaggerUi.serve,swaggerUi.setup(swaggerDocs));
app.use(OpenApiValidator.middleware({
    apiSpec: swaggerDocs,
    validateSecurity: {
        handlers: {
            JWT: JWT_SECURITY
        }
    },
    operationHandlers: {
        basePath: __dirname + "/../components/",
        resolver
    }

}))

app.use(express.static(`${__dirname}/../public`));

export default app;