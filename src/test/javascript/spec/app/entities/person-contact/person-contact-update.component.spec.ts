/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ContactchallengeTestModule } from '../../../test.module';
import { PersonContactUpdateComponent } from 'app/entities/person-contact/person-contact-update.component';
import { PersonContactService } from 'app/entities/person-contact/person-contact.service';
import { PersonContact } from 'app/shared/model/person-contact.model';

describe('Component Tests', () => {
    describe('PersonContact Management Update Component', () => {
        let comp: PersonContactUpdateComponent;
        let fixture: ComponentFixture<PersonContactUpdateComponent>;
        let service: PersonContactService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ContactchallengeTestModule],
                declarations: [PersonContactUpdateComponent]
            })
                .overrideTemplate(PersonContactUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PersonContactUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PersonContactService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new PersonContact(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.personContact = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new PersonContact();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.personContact = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
