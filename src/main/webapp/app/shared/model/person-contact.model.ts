export const enum PersonContactType {
    MAIN = 'MAIN',
    PHONE = 'PHONE',
    CELLPHONE = 'CELLPHONE',
    MAIL = 'MAIL',
    FAX = 'FAX',
    OTHER = 'OTHER'
}

export interface IPersonContact {
    id?: number;
    type?: PersonContactType;
    value?: string;
    personFullname?: string;
    personId?: number;
}

export class PersonContact implements IPersonContact {
    constructor(
        public id?: number,
        public type?: PersonContactType,
        public value?: string,
        public personFullname?: string,
        public personId?: number
    ) {}
}
