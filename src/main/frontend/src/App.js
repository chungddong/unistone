import {useEffect, useState} from "react";
import axios from "axios";
import './App.css';

function App() {
  const [hello, setHello] = useState('');

  useEffect(() => {
      axios.get('/api/test')
          .then((res) => {
              setHello(res.data);
          });
  }, []);

  return (
      <div className="App">
          백엔드 데이터 : {hello} 임니다.
      </div>
  );
}



export default App;
