import { createToken } from "../../lib/security.mjs";
import { loadByCredentials, loadById, addUser, loadByLogin, changeUser, removeUser} from "./repository.mjs";

export async function login({username, password}) {
    const user = await loadByCredentials(username, password);
    if (user) return {
        token: createToken(user),
        ...user
    };
    return null;
}

export async function getUserById(id) {
    return loadById(id);
}

export async function getUserByLogin(login) {
    return loadByLogin(login);
}

export async function createUser({username, password}){
    return addUser(username, password);
}

export async function updateUser(username, newPassword, newEmail, newRoles){
    return changeUser(username,newEmail,newPassword,newRoles);
}

export async function deleteUser(username){
    return removeUser(username);
}