
import './App.css'; // Import your CSS file for styling.
import { Outlet } from 'react-router-dom';
import LogoutButton from './components/Auth/LogoutButton';

const App = () => {


  return (
    <>
    <div>
      <Outlet />
      <LogoutButton /> 
    </div>
    </>
  );
};

export default App;
