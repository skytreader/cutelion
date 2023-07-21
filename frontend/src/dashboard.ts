import { html } from 'lit';
import {BaseTemplate} from "Frontend/src/base-template";
import {Project} from "Frontend/src/data-model";

class Dashboard extends BaseTemplate {
    private projects: Array<Project> = [];

    pageContent() {
        const body = this.projects.length == 0 ? html`
         <p>No projects found. <button id="start-project">Start New Project</button></p>
         ` :
         html`
         <button id="start-project">Start New Project</button>
         <ul>
         ${this.projects.map((project) =>
             html `<li><a href="project/edit/${project.id}">${project.name}</a></li>`
         )}
        </ul>
         `
        return html`
        <h2>Project Dashboard</h2>
        ${body}
        `;
    }
}

customElements.define("dashboard-page", Dashboard);