
interface IHobbyItemProps{
    hobby:string;
}

export function HobbyItem({hobby}: IHobbyItemProps){
    return (
        <li className="HobbyList-item">{hobby}</li>
    )
}