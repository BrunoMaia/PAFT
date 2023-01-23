import { json } from "express";
import {login, createUser, getUserById, getUserByLogin, updateUser, deleteUser} from "./service.mjs";

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
 *       '401':
 *         description: "User must be admin"
 *       '404':
 *         description: "User not found"
 *     security: 
 *       - JWT : ['ADMIN']
 */
export async function get_user(req, res, _) {  
  const user = await getUserById(parseInt(req.params.id));
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
  const user = await getUserByLogin(req.user.login);
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
 *             $ref: '#/components/schemas/Password'
 *     responses:                 
 *       '200':
 *         description: "User changed"
 *       '304':
 *         description: "Someting went wrong, and not changed"
 *     security:
 *       - JWT: ['USER']
 */
export async function put_user(req, res, _){
  const user = req.user;
  if(updateUser(user.login,req.body.password,'',user.roles)){
     res.sendStatus(200);
  }
  else {
    res.sendStatus(304)
  }
}

/**
 * @openapi
 * /users/me:
 *   delete:
 *     summary: "Removes the current user"
 *     tags:
 *       - "user"
 *     operationId: delete_user
 *     x-eov-operation-handler: user/router
 *     responses:                 
 *       '200':
 *         description: "User deleted"
 *       '400':
 *         description: "Invalid data provided"
 *       '401':
 *         description: "Not logged"
 *     security:
 *       - JWT : ['USER']
 */
export async function delete_user(req, res, _){
  if(deleteUser(req.user.login)){
    res.sendStatus(200)
  }
  else{
    res.sendStatus(400);
  }
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
  const info = {autor: "Bruno Maia", ano: 2023, versao: "1.0.0", github:"https://github.com/BrunoMaia/"};
  return res.json(info);
}
