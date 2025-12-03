import { useEffect, useState } from "react";
import axios from "axios";
import styled from "styled-components";
import './App.css'

// Container principal
const Container = styled.div`
  padding: 40px;
  font-family: Arial, sans-serif;
  
  // Ocupa no máximo 600px, permitindo que a tela seja menor.
  max-width: 600px; 
  
  // Centraliza o bloco Container horizontalmente na tela.
  margin: 0 auto; 
`;

// Grupo de Input e Botão (centralizado dentro do Container)
const FormGroup = styled.div`
  display: flex; 
  align-items: center; 
  gap: 10px;
  width: 100%; 
  max-width: 400px; 
  margin: 20px auto; 
`;

// Título principal
const Title = styled.h1`
  color: #333;
  margin-bottom: 20px;
  text-align: center;
`;

// Input
const Input = styled.input`
  padding: 10px;
  font-size: 16px;
  border-radius: 6px;
  border: 1px solid #ccc;
  outline: none;

  width: 100%; 

  &:focus {
    border-color: #007bff;
  }
`;

// Botão
const Button = styled.button`
  padding: 10px 20px;
  font-size: 16px;
  border-radius: 6px;
  border: none;
  background-color: #007bff;
  color: white;
  cursor: pointer;

  &:hover {
    background-color: #0056b3;
  }
`;

const Title2 = styled.h2`
  color: #555;
  margin-top: 40px;
  text-align: center;
`;

// Lista
const UserList = styled.ul`
  margin-top: 20px;
  list-style: none;
  padding: 0;
`;

const UserItem = styled.li`
  padding: 8px 0;
  border-bottom: 1px solid #eee;
`;

const API_BASE_URL = "http://localhost:8080/api/v1/users";

interface User {
  id: number;
  name: string;
}

function App() {
  const [users, setUsers] = useState<User[]>([]);
  const [name, setName] = useState("");

  useEffect(() => {
    axios.get(API_BASE_URL)
      .then(response => setUsers(response.data))
      .catch(error => console.log(error));
  }, []);

  const saveUser = () => {
    axios.post(API_BASE_URL, { name })
      .then(() => window.location.reload())
      .catch(error => console.log(error));
  };
  const deleteUser = async (id: number) => {
    try {
      await axios.delete(`${API_BASE_URL}/${id}`);
      console.log(`Usuário com ID ${id} excluído com sucesso.`);
      setUsers(prevUsers => prevUsers.filter(user => user.id !== id));

    } catch (error) {
      if (axios.isAxiosError(error) && error.response) {
         console.error(`Erro ao excluir o usuário ${id}:`, error.response.status, error.response.data);
      } else {
         console.error("Erro desconhecido:", error);
      }
    }
  };
  return (
    <>
      <Container>
        <Title>CRUD Frontend + Spring Boot</Title>
        <FormGroup>
          <Input
            placeholder="Digite um nome"
            value={name}
            onChange={e => setName(e.target.value)}
          />
          <Button onClick={saveUser}>Salvar</Button>
        </FormGroup>

        <Title2>Lista de usuários:</Title2>
        <UserList>
          {users.map(u => (
            <div key={u.id} style={{ display: 'flex', alignItems: 'center', marginBottom: '8px' }}>
                <UserItem style={{ flexGrow: 1 }}>{u.name}</UserItem>
                <Button 
                    onClick={() => deleteUser(u.id)}
                    style={{ marginLeft: '10px', backgroundColor: 'red' }} // Estilo de exemplo
                >
                    Excluir
                </Button>
            </div>
          ))}
        </UserList>
      </Container>
    </>
  )
}

export default App
