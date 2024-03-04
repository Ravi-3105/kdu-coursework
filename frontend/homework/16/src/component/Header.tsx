import { Search } from './Search'

export const h1: React.CSSProperties = {
    alignItems: 'center',
    color: 'white',
    margin: 'auto',
    width: '100%',
    backgroundColor: '#28a745',
  };
  
  export const headerDiv: React.CSSProperties = {
    width: '80%',
    margin: 'auto',
    display: 'flex',
    justifyContent: 'space-around',
  };
  

export function Header() {
    return (
        <h1 style={h1}><div style={headerDiv}><p>Item Lister</p><Search/></div></h1>
    )
}