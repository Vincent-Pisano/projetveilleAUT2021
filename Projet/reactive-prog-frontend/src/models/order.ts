import { Item } from "./item";
import { User } from "./user";

export class Order {

    idOrder: number | undefined;
    totalPrice: number | undefined;
    status: string | undefined;
    items: Array<Item> | undefined;
    user: User | undefined;

}
