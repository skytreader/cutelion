import { html } from 'lit';
import {BaseTemplate} from "Frontend/src/base-template";
import {Project} from "Frontend/src/data-model";

class Dashboard extends BaseTemplate {
    private projects: Array<Project> = [];

    protected override pageContent() {
        const body = this.projects.length == 0 ? html` <p>No projects found. <button id="start-project">+ Start New Project</button></p> ` : html`
         <vaadin-horizontal-layout theme="spacing padding" style="justify-content: end">
           <vaadin-button theme="primary" id="start-project">+ New</vaadin-button>
           <vaadin-button theme="primary error" id="delete-project">- Delete</vaadin-button>
         </vaadin-horizontal-layout>
         <vaadin-list-box style="border: 1px solid #edf2f4">
         ${this.projects.map((project) =>
            html `<vaadin-item><a href="project/edit/${project.id}">${project.name}</a></vaadin-item><hr>`
        )}
        </vaadin-list-box>
         `;
        return html`
        <h2>Project Dashboard</h2>
        ${body}
        `;
    }
}

customElements.define("dashboard-page", Dashboard);