import {IListItem} from './List';

export interface IState {
    listItems: IListItem[],
    state: React.Dispatch<React.SetStateAction<{
      id: number;
      text: string;
    }[]>>
  }