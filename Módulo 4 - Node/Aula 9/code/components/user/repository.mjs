import { prisma } from "../../lib/database.mjs";
import bcrypt from "bcrypt";

// Models

const USER_FIELDS = {
    id: true,
    username: true,
    name: true,
    roles: true,
    password: false
}
const FULL_USER = {
    id: true,
    username: true,
    name: true,
    roles: true,
    password: true
}

// CRUD Users

export async function loadById(id) {
    return await prisma.user.findUnique({where:{id}, select: USER_FIELDS});
}

export async function loadByCredentials(username, password) {
    const user = await prisma.user.findUnique({where:{username}, select: FULL_USER});
    if(!user) return null;
    if(!await bcrypt.compare(password, user.password)) return null;
    delete user.password;
    return user;
}

export async function loadByLogin(username){
    const user = await prisma.user.findUnique({where:{username}, select: USER_FIELDS});
    if(!user) return null;
    return user;
}

export async function addUser(username, password, email =''){
    const userPassword = await bcrypt.hash(password,10);
    const user= await prisma.user.create({data:{
        username:username,
        password:userPassword,
        roles: {
            connect: [
                {name: 'USER'},
            ]
        }
    }})
    delete user.password;
    return user;
}

export async function removeUser(username){
    await prisma.user.delete({where: {username:username}});
    return true;
}

export async function changeUser(username, email = '', password, roles = ['USER']){
    const newPassword = await bcrypt.hash(password,10);
    await prisma.user.update({
        where:{username:username},
        data: {
            password: newPassword,

        }
    });
    return true;
}

// CRUD Fin