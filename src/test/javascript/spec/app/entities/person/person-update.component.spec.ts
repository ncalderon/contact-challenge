/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ContactchallengeTestModule } from '../../../test.module';
import { PersonUpdateComponent } from 'app/entities/person/person-update.component';
import { PersonService } from 'app/entities/person/person.service';
import { Person } from 'app/shared/model/person.model';

describe('Component Tests', () => {
    describe('Person Management Update Component', () => {
        let comp: PersonUpdateComponent;
        let fixture: ComponentFixture<PersonUpdateComponent>;
        let service: PersonService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ContactchallengeTestModule],
                declarations: [PersonUpdateComponent]
            })
                .overrideTemplate(PersonUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PersonUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PersonService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Person(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.person = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Person();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.person = entity;
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
