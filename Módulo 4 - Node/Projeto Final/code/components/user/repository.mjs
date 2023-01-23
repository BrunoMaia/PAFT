let users = {
    convidado: {id: 1, login:'convidado', password: 'convidado', admin: false, roles: ['USER']},
    bruno_maia: {id: 2, login: 'bruno_maia', email: 'brunomaia@alunos.utfpr.edu.br', password: 'uma senha muito forte', admin: true, roles: ['ADMIN','USER']},
    id: 2,
    undefined: {id:0, login: 'ERROR',password: null, roles: null},
};

let fin = {

};

// Formating

function formatUser(user) {
    if (!user) return user;    
    return {...user, password: undefined};
}

// CRUD Users

export async function loadById(id) {
    for(const [key, value] of Object.entries(users)){
        if(value.id === id){
            return formatUser(value);
        }
    }
}

export async function loadByCredentials(username, password) {
    if(users[username].password === password){
        return formatUser(users[username]);
    }
}

export async function loadByEmail(email) {
    for(const [key, value] of Object.entries(users)){
        if(value.email === email){
            return formatUser(value);
        }
    }
}

export async function loadByLogin(username){
    return formatUser(users[username])
}

export async function addUser(username, password, email =''){
    const id = users.id + 1;
    if(username === 'id') throw Error(`Invalid username: ${username}`);
    users[username] = ({id: id, login: username, email: email, password: password, roles: ['USER']});
    users.id ++;
    return loadByCredentials(username, password);
}

export async function removeUser(username){
    delete users[username];
    return true;
}

export async function changeUser(username, email = '', password, roles = ['USER']){
    users[username] = {email: email, password: password, roles: roles};
    return true;
}

// CRUD Fin