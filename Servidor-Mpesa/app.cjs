require('dotenv').config(); // Carregar as variáveis de ambiente

const express = require('express');
const mpesa = require('mpesa-node-api');
const crypto = require('crypto');
const cors = require('cors');  // Importando o CORS

const app = express();

// Habilitando CORS para todas as origens
app.use(cors());

// Middleware para parsear o corpo da requisição em JSON
app.use(express.json());

// Função para gerar uma referência aleatória
const gerarReferencia = () => {
    return crypto.randomBytes(16).toString('hex'); // Gera uma referência aleatória
};

// Endpoint para iniciar a transação C2B
app.post('/iniciar-transacao', (req, res) => {
    const { numeroCelular, valor } = req.body; // Pega o número de celular e o valor do corpo da requisição

    if (!numeroCelular) {
        return res.status(400).json({ mensagem: 'Número de celular é obrigatório!' });
    }

    if (!valor || valor <= 0) {
        return res.status(400).json({ mensagem: 'Valor de transação é obrigatório e deve ser maior que zero!' });
    }

    const referencia = gerarReferencia(); // Gera a referência aleatória

    // Inicia a transação C2B
    mpesa.initiate_c2b(valor, numeroCelular, 'T12344C', referencia)  // Passa o valor junto com os outros parâmetros
        .then(function(response) {
            console.log("Resposta C2B:", response);
            return res.json({ mensagem: 'Transação iniciada com sucesso!', resposta: response });
        })
        .catch(function(error) {
            console.error("Erro C2B:", error);
            return res.status(500).json({ mensagem: 'Erro ao iniciar a transação!', erro: error });
        });
});

// Inicia o servidor na porta 3000
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
    console.log(`Servidor rodando na porta ${PORT}`);
});
