import { html, nothing } from 'lit';
import { BaseTemplate } from "Frontend/src/base-template";
import {FormLayoutResponsiveStep} from "@vaadin/form-layout/vaadin-form-layout";
import {Project} from "Frontend/src/data-model";

class ProjectWorksheet extends BaseTemplate {
    private project?: Project;

    // https://vaadin.com/docs/latest/components/form-layout
    private responsiveSteps: FormLayoutResponsiveStep[] = [
        {minWidth: 0, columns: 1},
        {minWidth: '320px', columns: 2},
        {minWidth: '550px', columns: 4}
    ];
    protected override pageContent() {
        const hasProject = this.project !== undefined;
        const headerText = hasProject ? `Edit Project - ${this.project?.name}` : "New" +
            " Project";
        const buttonText = hasProject ? "Save Project" : "Create Project";
        return html`
        <h2>${headerText}</h2>
        <vaadin-horizontal-layout theme="spacing">
            <vaadin-form-layout id="project-details-form" .responsiveSteps="${this.responsiveSteps}">
            <vaadin-text-field id="project-name" label="Name"></vaadin-text-field>
            <vaadin-text-field type="text" id="default-language" label="Default Language" placeholder="en-US"></vaadin-text-field>
            </vaadin-form-layout>
            <vaadin-button id="persist-project" theme="primary" style="top: 20px;">${buttonText}</vaadin-button>
        </vaadin-horizontal-layout>
        <vaadin-horizontal-layout ?hidden="${!hasProject}">
            <vaadin-form-layout id="translation-form" .responsiveSteps="${this.responsiveSteps}">
                <vaadin-text-field id="translation-key" label="Translation Key"></vaadin-text-field>
                <vaadin-text-field id="translation-value" label="String value"></vaadin-text-field>
            </vaadin-form-layout>
            <vaadin-button id="add-translation" theme="secondary" style="top: 20px;">Add Translation</vaadin-button>
        </vaadin-horizontal-layout>
        <vaadin-grid .items="${this.project?.translations}">
            <vaadin-grid-column flex-grow="1" path="key"></vaadin-grid-column>
            <vaadin-grid-column flex-grow="1" path="value"></vaadin-grid-column>
        </vaadin-grid>
        `;
    }
}

customElements.define("project-worksheet", ProjectWorksheet)