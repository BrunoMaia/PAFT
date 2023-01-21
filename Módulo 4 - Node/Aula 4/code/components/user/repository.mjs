let users = [
    {id: 1, login:'convidado', password: 'convidado', admin: false},
];

function formatUser(user) {
    if (!user) return user;    
    return {...user, password: undefined};
}

export async function loadById(id) {
    return formatUser(users.find(u => u.id === id));
}

export async function loadByCredentials(username, password) {
    return formatUser(
        users.find(u => 
            u.login === username && 
            u.password === password
        )
    );
}

export async function addUser(username, password){
    const id = users[users.length - 1].id + 1;
    users.push({id: id, login: username, password: password, admin: false});
    return loadByCredentials(username, password);
}