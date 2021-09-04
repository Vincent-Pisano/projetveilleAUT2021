import { Item } from "./item";
import { User } from "./user";

export class Comment {

    idComment: number | undefined;
    comment: string | undefined;
    item: Item | undefined;
    user: User | undefined;

}
