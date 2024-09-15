import { BrowserRouter, Route, Routes } from 'react-router-dom'
import { Home } from './component/Home'
import { ListProvider } from './context/Context'
import { Detail } from './component/Detail'

function App() {

  return (
    <BrowserRouter>
      <ListProvider>
        <Routes>
          <Route path="/home" element={<Home />} />
          <Route path="/home/:id" element={<Detail />} />
        </Routes>
      </ListProvider>
    </BrowserRouter>
  )
}

export default App
