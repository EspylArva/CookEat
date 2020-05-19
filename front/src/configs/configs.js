const isProd = process.env.NODE_ENV === 'production';

export const configs = {
    env: process.env.NODE_ENV,
    apiUrl: isProd ? 'https://api.cookeat.garageisep.com' : 'http://localhost:8080',

    match: {
        quantity: 5
    }
}