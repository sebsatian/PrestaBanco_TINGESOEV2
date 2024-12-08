import { useEffect, useState } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import trackingService from '../services/tracking.service.js';
import 'bootstrap/dist/css/bootstrap.min.css';
import '../styles/ViewRequests.css';

const TrackRequests = () => {
  const [trackings, setTrackings] = useState([]);
  const [error, setError] = useState('');
  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    console.log('Location state:', location.state); // Verify the location state

    const fetchTrackings = async () => {
      try {
        let response;
        if (location.state?.rut) {
          response = await trackingService.getTrackingsByRut(location.state.rut);
        } else {
          response = await trackingService.getAllTrackings();
        }
        const trackingsData = response.data;
        trackingsData.sort((a, b) => b.id - a.id); // Sort by ID in descending order

        setTrackings(trackingsData);
      } catch (error) {
        console.error('Error al obtener los seguimientos:', error);
        setError('No se pudo obtener los seguimientos. Por favor, vuelva a intentarlo.');
      }
    };

    fetchTrackings();
  }, [location.state]);

  if (error) {
    return <div className="alert alert-danger mt-4">{error}</div>;
  }

  return (
    <div className="container-fluid mt-5">
      <h2 className="text-center mb-4">Lista de Seguimientos</h2>
      <div className="table-responsive">
        <table className="table table-striped table-hover table-bordered align-middle">
          <thead className="table-dark">
            <tr>
              <th className="text-center">#</th>
              <th className="text-center">RUT del Cliente</th>
              <th className="text-center">Tipo</th>
              <th className="text-center">Estado</th>
              <th className="text-center">Acciones</th>
            </tr>
          </thead>
          <tbody>
            {trackings.map((tracking, index) => (
              <tr key={tracking.id}>
                <th scope="row" className="text-center">{index + 1}</th>
                <td className="text-center">{tracking.clientRut}</td>
                <td className="text-center">{tracking.type}</td>
                <td className="text-center">{tracking.status}</td>
                <td className="text-center">
                  <button
                    className="btn btn-primary"
                    onClick={() => {
                      console.log('Received state:', location.state);
                      navigate(`/request-details/${tracking.requestId}`, { state: { from: 'cliente' } });
                    }}
                  >
                    Ver Detalles
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default TrackRequests;