import { HobbyItem } from './HobbyItem';
import './HobbyList.css'

export interface IHobbyItem {
    id: number;
    hobby: string;
}

interface IHobbyProps {
    hobbyItems: IHobbyItem[];
}

export function HobbyList({ hobbyItems }: IHobbyProps) {

    return (
        <div className='hobby'>
            <div className='hobbyHead'>Hobbies</div>
            <ul id="hobbyList">
                {
                    hobbyItems.map((item) => {
                        return (
                            <HobbyItem key={item.id} hobby={item.hobby} />
                        )
                    })
                }
            </ul>
        </div>
    )
}