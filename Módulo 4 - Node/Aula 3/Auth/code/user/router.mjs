import { getUser, login } from "./service.mjs";

/**
 * @openapi
 * /users/login:
 *   post:
 *     summary: "Logs in the user"
 *     tags:
 *       - "auth"
 *     operationId: userLogin
 *     x-eov-operation-handler: router
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

export async function userGet(req, res, _) {
  let id = parseInt(req.params.id);
  if (isNaN(id)) {
    res.status(400).send("Invalid id!");
  }

  const user = await getUser(id);
  return user ? res.json(user) : res.sendStatus(404);  
};
