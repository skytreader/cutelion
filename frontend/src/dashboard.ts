import { html, LitElement } from 'lit';

class Dashboard extends LitElement {
    render() {
        return html`
        <div>
            <h1>CuteL10N App</h1>
        </div>
        `
    }
}

customElements.define("dashboard-page", Dashboard);