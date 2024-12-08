import http from '../http-common';

const registerClient = (clientData) => {
  return http.post('/client/register', clientData);
};

export default {
  registerClient
};
