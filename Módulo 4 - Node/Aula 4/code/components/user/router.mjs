import { json } from "express";
import { getUser, login, createUser} from "./service.mjs";

/**
 * @openapi
 * /users/login:
 *   post:
 *     summary: "Logs in the user"
 *     tags:
 *       - "auth"
 *     operationId: userLogin
 *     x-eov-operation-handler: user/router
 *     requestBody: 
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             $ref: '#/components/schemas/UsernamePassword'
 *     responses:                 
 *       '200':
 *         description: "User logged in"
 *       '400':
 *         description: "Invalid data provided"
 *       '401':
 *         description: "Login failed"
 */
export async function userLogin(req, res, _) {
  const user = await login(req.body);  
  return user ? res.json(user) : res.sendStatus(401);
};

/**
 * @openapi
 * 
 * /users/{id}:
 *   get:
 *     summary: "Retrieves user information"
 * 
 *     tags:
 *       - "user"
 * 
 *     parameters:
 *       - $ref: "#/components/parameters/Id"
 * 
 *     operationId: get_user
 *     x-eov-operation-handler: user/router
 * 
 *     responses:
 *       '200':
 *         description: "Returns the user"
 *       '404':
 *         description: "User not found"
 *     security: 
 *       - JWT : ['ADMIN']
 */
export async function get_user(req, res, _) {  
  const user = await getUser(parseInt(req.params.id));
  return user ? res.json(user) : res.sendStatus(404);  
}

/**
 * @openapi
 * 
 * /users/me:
 *   get:
 *     summary: "Retrieves self user information"
 * 
 *     tags:
 *       - "user"
 *     operationId: get_me
 *     x-eov-operation-handler: user/router
 *     responses:
 *       '200':
 *         description: "Returns the user"
 *       '404':
 *         description: "User not found"
 *     security:
 *       - JWT: ['USER']
 */
export async function get_me(req, res, _) {  
  const user = await getUser(parseInt(req.user.id));
  return user ? res.json(user) : res.sendStatus(404);  
}

/**
 * @openapi
 * /users:
 *   post:
 *     summary: "Create new user"
 *     tags:
 *       - "user"
 *     operationId: create_User
 *     x-eov-operation-handler: user/router
 *     requestBody: 
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             $ref: '#/components/schemas/UsernamePassword'
 *     responses:                 
 *       '201':
 *         description: "User created"
 *       '400':
 *         description: "Invalid data provided"
 *     security:
 *       - JWT: []
 */    

export async function create_User(req, res, _){
  const user = await createUser(req.body);
  return user ? res.json(user) : res.sendStatus(400);
}

/**
 * @openapi
 * /users/me:
 *   put:
 *     summary: "Editates the current user"
 *     tags:
 *       - "user"
 *     operationId: put_user
 *     x-eov-operation-handler: user/router
 *     requestBody: 
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             $ref: '#/components/schemas/UsernamePassword'
 *     responses:                 
 *       '200':
 *         description: "User changed"
 *       '400':
 *         description: "Invalid data provided"
 *     security:
 *       - JWT: []
 */
export async function put_user(req, res, _){
  return true;
}

/**
 * @openapi
 * /users/me:
 *   delete:
 *     summary: "Removes the current user"
 *     tags:
 *       - "user"
 *     operationId: delete_ser
 *     x-eov-operation-handler: user/router
 *     responses:                 
 *       '200':
 *         description: "User deleted"
 *       '400':
 *         description: "Invalid data provided"
 *       '401':
 *         description: "Not logged"
 *     security:
 *       - JWT : []
 */
export async function delete_user(req, res, _){
  return true;
}


/**
 * @openapi
 * /info:
 *   get:
 *     summary: "Show the info about the API"
 *     tags:
 *       - about
 *     operationId: aboutAPI
 *     x-eov-operation-handler: user/router
 *     responses:
 *       '200':
 *         description: "Returns the info"
 */
export async function aboutAPI(req, res, _){
  const info = {autor: "Bruno Maia", ano: "2022 - 2023"};
  return res.json(info);
}
