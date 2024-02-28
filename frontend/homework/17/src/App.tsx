import { BrowserRouter, Route, Routes } from 'react-router-dom'
import { Home } from './component/Home'
import { Detail } from './component/Detail'
function App() {
  
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/home" element={<Home />} />
        <Route path="/home/:id" element={<Detail />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
