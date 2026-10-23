# Digital Library Management System: Project Context

## Purpose

A learning project and REST backend for managing library users, books, borrowing, returns, and borrowing history. Keep changes practical and focused; preserve the existing architecture unless there is a clear reason to change it.

This document is a handover, not proof that listed features currently work. Inspect source and build configuration before making code-specific changes.

## Stack

- Java 17
- Spring Boot 4.0.8
- Spring MVC, Spring Data JPA, Hibernate, PostgreSQL
- Lombok and Jakarta Validation
- Maven

Verify `pom.xml` before relying on these versions or dependencies. Keep database credentials and other secrets out of source control; prefer environment variables or external configuration.

## Architecture

Use the existing layers:

- Controllers handle HTTP input/output and delegate to services.
- Services implement business rules and coordinate operations.
- Repositories use Spring Data JPA.
- Entities map persistence; DTOs define API request/response shapes.

Avoid business logic in controllers, repository access from controllers, and exposing password data in API responses.

## Domain Rules

The main entities are `User`, `Book`, and `BorrowRecords`. Verify their current fields and mappings before writing queries.

- A `BorrowRecords` row with `returnDate == null` is an active borrowing.
- Borrowing requires an existing user, an existing book, and available copies. Create a history row and decrement copies.
- Returning requires a matching active record. Set its return date and increment copies exactly once.
- Do not allow copies to become negative. Borrow and return should update inventory and history transactionally.
- A transaction alone does not prevent two concurrent requests from borrowing the final copy. Consider row locking or an atomic conditional update after basic flows are tested.

## API and Persistence Notes

- Use Spring Data pagination types: `org.springframework.data.domain.Page`, `Pageable`, `PageRequest`, and `Sort`. Do not use Springdoc's similarly named `Pageable`.
- Sort by Java entity property names, not database column names. Validate or whitelist client-provided sort fields.
- Prefer DTOs for responses where entity serialization could expose sensitive fields or create nested/lazy-loading problems.
- Validate request DTOs with `@Valid` where applicable. Validate required strings, nonnegative copies, IDs, and pagination inputs.
- Map expected errors to meaningful HTTP responses (for example, invalid input, missing resources, or business conflicts); do not return stack traces to clients.
- Preserve borrowing history when deleting books; decide whether deletion should be prevented or use soft deletion.

## Recent Verified Work

- `pom.xml` configures Java 17 and Spring Boot 4.0.8.
- Spring Data pagination imports were corrected to `org.springframework.data.domain.Pageable`.
- Book sorting was corrected to use camelCase entity properties while retaining explicit database column names.
- A Maven compile and the existing Spring Boot context test passed after those changes. This does not confirm every HTTP endpoint works.

Re-check source before relying on this snapshot; user or tool edits may have happened since verification.

## Next Priorities

1. Exercise the actual user, book, borrow, return, and history endpoints with an HTTP client.
2. Confirm duplicate registration behavior and secure password handling; never return passwords from APIs.
3. Test pagination, sorting, and invalid sort/page inputs.
4. Test borrow/return effects on both available copies and borrowing history, including duplicate returns and unavailable books.
5. Review validation, exception-to-HTTP mapping, delete behavior, and concurrency protection.
6. Add focused tests for important service and controller behavior.

Use a safe development database for destructive or rollback tests.

## Working Preferences

- Inspect current source before making code-specific claims; treat the repository as the source of truth.
- Make small, focused changes and verify them before moving to another task.
- Do not invent paths, fields, methods, routes, or dependencies.
- Explain technical terms briefly and use clear, beginner-friendly language.
- Do not add full Spring Security, JWT, or session authentication unless requested.
- State uncertainty rather than guessing. Never claim a file was changed unless it was actually edited.
