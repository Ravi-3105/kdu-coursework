import './Name.css'

export interface INameProps{
    name:string;
}
export function Name({name}:INameProps) {
  return (
    <div className='name'>{name}</div>
  )
}
