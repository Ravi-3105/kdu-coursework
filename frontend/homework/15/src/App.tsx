import { ScrollToTop } from './ScrollToTop';
import { Form } from './Form';
import { Timer } from './Timer';
import './App.scss'

const App = () => {
  return (
    <div className='center'>
      <div className='middle'>

        <div className='head'>TIMER AND USEREF</div>
        <Form />
        <div className='timer'>
          <Timer />
        </div>
        <div className='scroll'><ScrollToTop /></div>
        <div className='scroll'><ScrollToTop /></div>
        <div className='scroll'><ScrollToTop /></div>
        <div className='scroll'><ScrollToTop /></div>
      </div>
    </div>
  );
};

export default App;
