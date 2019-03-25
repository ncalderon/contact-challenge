import { Moment } from 'moment';
import { IAddress } from 'app/shared/model/address.model';
import { IPersonContact } from 'app/shared/model/person-contact.model';

export const enum PersonDocumentType {
    ID = 'ID',
    PASSPORT = 'PASSPORT'
}

export const enum Gender {
    MALE = 'MALE',
    FEMALE = 'FEMALE'
}

export interface IPerson {
    id?: number;
    fullname?: string;
    documentNumber?: string;
    documentType?: PersonDocumentType;
    birthday?: Moment;
    gender?: Gender;
    userLogin?: string;
    userId?: number;
    addresses?: IAddress[];
    personContacts?: IPersonContact[];
}

export class Person implements IPerson {
    constructor(
        public id?: number,
        public fullname?: string,
        public documentNumber?: string,
        public documentType?: PersonDocumentType,
        public birthday?: Moment,
        public gender?: Gender,
        public userLogin?: string,
        public userId?: number,
        public addresses?: IAddress[],
        public personContacts?: IPersonContact[]
    ) {}
}
