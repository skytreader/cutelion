import { html } from 'lit';
import { BaseTemplate } from "Frontend/src/base-template";
import { Project } from "Frontend/src/data-model";

class ProjectWorksheet extends BaseTemplate {
    private project?: Project;
    pageContent() {
        const hasProject = this.project !== undefined;
        const headerText = hasProject ? "Edit Project" : "New Project";
        const buttonText = hasProject ? "Save Project" : "Create Project";
        return html`
        <h2>${headerText}</h2>
        <div>
        <vaadin-form-layout id="project-details-form">
        <vaadin-text-field id="project-name" label="Name"></vaadin-text-field>
        <vaadin-text-field type="text" id="default-language" label="Default Language" placeholder="en-US"></vaadin-text-field>
        <button id="persist-project">${buttonText}</button>
        </vaadin-form-layout>
</div>
        `
    }
}

customElements.define("project-worksheet", ProjectWorksheet)