import { prisma } from "../../lib/database.mjs";
import bcrypt from "bcrypt";

function formatUser(user) {
    if (!user) return user;    
    return {...user, password: undefined};
}

export async function loadById(id) {
    return formatUser(await prisma.user.findUnique({where: {id}}));
}

export async function loadByCredentials(username, password) {
    const user = await prisma.user.findUnique({where: {username}});
    if(!user) return null;
    if(!await bcrypt.compare(password, user.password)) return null;
    return formatUser(user);
}