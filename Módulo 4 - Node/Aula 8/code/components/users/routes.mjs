import { getUser, login } from "./service.mjs";

/**
 * @openapi
 * /users/login:
 *   post:
 *     summary: "Logs in the user"
 * 
 *     tags:
 *       - "auth"
 *     
 *     operationId: user_login
 *     x-eov-operation-handler: users/routes
 * 
 *     requestBody:
 *       description: Login information
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             $ref: "#/components/schemas/UsernamePassword" 
 * 
 *     responses:
 *       '200':
 *         description: "User logged in"
 *       '400':
 *         description: "Invalid data provided"
 *       '401':
 *         description: "Login failed"
 */
export async function user_login(req, res, _) {
  const user = await login(req.body);
  return user ? res.json(user) : res.sendStatus(401);
}

/**
 * @openapi
 * 
 * /users/me:
 *   get:
 *     summary: "Retrieves user information"
 * 
 *     tags:
 *       - "profile"
 * 
 *     operationId: get_user
 *     x-eov-operation-handler: users/routes
 * 
 *     responses:
 *       '200':
 *         description: "Returns the user"
 *       '404':
 *         description: "User not found"
 *
 *     security: 
 *       - {}
 *       - JWT: ['USER']
 */
export async function get_user(req, res, _) {  
  if (!req.user) res.send("Hello guest!");

  const user = await getUser(parseInt(req.user.id));
  return user ? res.json(user) : res.sendStatus(404);  
}