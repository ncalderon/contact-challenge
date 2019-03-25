import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPersonContact } from 'app/shared/model/person-contact.model';

type EntityResponseType = HttpResponse<IPersonContact>;
type EntityArrayResponseType = HttpResponse<IPersonContact[]>;

@Injectable({ providedIn: 'root' })
export class PersonContactService {
    public resourceUrl = SERVER_API_URL + 'api/person-contacts';

    constructor(protected http: HttpClient) {}

    create(personContact: IPersonContact): Observable<EntityResponseType> {
        return this.http.post<IPersonContact>(this.resourceUrl, personContact, { observe: 'response' });
    }

    update(personContact: IPersonContact): Observable<EntityResponseType> {
        return this.http.put<IPersonContact>(this.resourceUrl, personContact, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPersonContact>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPersonContact[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
