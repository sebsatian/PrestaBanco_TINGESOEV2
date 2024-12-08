import httpClient from '../http-common';

const getAllTrackings = () => {
    return httpClient.get('/tracking/all');
};

const getTrackingsByRut = (rut) => {
    return httpClient.get(`/tracking/getByRut/${rut}`);
};

const updateTracking = (trackingId, status) => {
    return httpClient.post(`/tracking/update/${trackingId}`, { status });
};


const createTracking = (requestId, clientRut, type) => {
    const trackingData = {
        requestId: requestId,
        type: type,
        clientRut: clientRut,
        status: "En Revision Inicial"
    };
    return httpClient.post('/tracking/create', trackingData);
};

export default {
    getAllTrackings,
    getTrackingsByRut,
    updateTracking,
    createTracking,
};